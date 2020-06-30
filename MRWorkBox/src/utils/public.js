// 去除查询条件首尾空格，文字中间空格不去掉
function strim (str) {
  if (str) {
    return str.replace(/(^\s*)|(\s*$)/g, '')
  } else {
    return ''
  }
}
// 转化树的格式
function replaceJson (ary) {
  let arr = []
  ary.forEach(value => { // ary[i] == value
    let obj = {}
    obj.id = value.id
    obj.pid = value.parentId
    obj.title = value.name
    if (value.children) {
      obj.children = replaceJson(value.children)
    } else {
      obj.checked = value.checkFlag
      if (obj.checked === 'null') {
        obj.checked = false
      }
    }
    arr.push(obj)
  })
  return arr
}
// 获取删除的和新增的数组的方法
function treearrayDiff (a, b) {
  for (var i = 0; i < b.length; i++) {
    for (var j = 0; j < a.length; j++) {
      if (a[j].id === b[i].id) {
        a.splice(j, 1)
        j = j - 1
      }
    }
  }
  return a
}
// 转换时间格式
function dateFormat (fmt, date) {
  if (date === '') {
    return null
  }
  var o = {
    'M+': date.getMonth() + 1, // 月份
    'd+': date.getDate(), // 日
    'H+': date.getHours(), // 小时
    'm+': date.getMinutes(), // 分
    's+': date.getSeconds(), // 秒
    'q+': Math.floor((date.getMonth() + 3) / 3), // 季度
    'S': date.getMilliseconds() // 毫秒
  }
  if (/(y+)/.test(fmt)) {
    fmt = fmt.replace(RegExp.$1, (date.getFullYear() + '').substr(4 - RegExp.$1.length))
  }
  for (var k in o) {
    if (new RegExp('(' + k + ')').test(fmt)) {
      fmt = fmt.replace(RegExp.$1, (RegExp.$1.length === 1) ? (o[k]) : (('00' + o[k]).substr(('' + o[k]).length)))
    }
  }
  return fmt
}
function zeroReg (timeStr) {
  if (timeStr.parseInt < 10) {
    return '0' + timeStr
  } else {
    return timeStr
  }
}
let pub = {}
// 去除查询条件是首尾空格，文字中间空格不去掉
pub.strim = function (str) {
  return strim(str)
}
// 转化树的格式
pub.replaceJson = function (data) {
  return replaceJson(data)
}
// 获取删除的和新增的数组的方法
pub.treearrayDiff = function (m, n) {
  return treearrayDiff(m, n)
}
// 转换时间格式
pub.dateFormat = function (fmt, date) {
  if (!date) {
    return ''
  }
  if (typeof date === 'string') {
    date = new Date(date)
  }
  return dateFormat(fmt, date)
}
// 字符串转时间类型(yyyy-MM-dd HH:mm:ss)，可只传入日期或时间，格式完全不正确时返回空字符串
pub.dateParse = function (fmt) {
  if (!fmt) {
    // 没有入参时，默认日期为今日,时间为零点
    let date = new Date()
    date.setHours(0)
    date.setMinutes(0)
    date.setSeconds(0)
    return date
  }
  let fmts = fmt.split(' ')
  let dateStrs = fmts[0].split('-')
  if (dateStrs.length < 3) {
    let timeStrs = fmts[0].split(':')
    if (timeStrs.length < 3) {
      return ''
    }
    let date = new Date()
    date.setHours(timeStrs[0])
    date.setMinutes(timeStrs[1])
    date.setSeconds(timeStrs[2])
    return date
  }
  let date = new Date()
  date.setFullYear(dateStrs[0])
  date.setMonth(dateStrs[1] - 1)
  date.setDate(dateStrs[2])
  switch (fmts.length) {
    case 1:
      // 只传入年月日，时间为00:00:00
      date.setHours(0)
      date.setMinutes(0)
      date.setSeconds(0)
      return date
    case 2:
      // 传入年月日-时分秒
      let timeStrs = fmts[1].split(':')
      if (timeStrs.length < 3) {
        return ''
      }
      date.setHours(zeroReg(timeStrs[0]))
      date.setMinutes(zeroReg(timeStrs[1]))
      date.setSeconds(zeroReg(timeStrs[2]))
      return date
    default:
      return ''
  }
}

// 时间格式解析
pub.dateFormatAnalysis =function(time1,time2,timeType){
  if (!time1&& !time2 && !timeType) {
    return ''
  }
    return (time1+'-'+time2+ '@'+ timeType+'#')

}
// 时间中反解析取星期几
pub.weekFormatReverse = function(dateStr){
  if(dateStr.includes("@")){
    return (JSON.stringify(dateStr).substr(dateStr.length, [1]))
  } else {
    return ''
  }
}

// 时间格式反向解析
pub.daterFormatReverse=function(dateStr){

  if(dateStr.includes("@")){
    return JSON.stringify(dateStr).substr(1,dateStr.length-2)
  } else {
    //传入时间格式为 (yyyy-MM-dd HH:mm:ss-yyyy-MM-dd HH:mm:ss)
    return JSON.stringify(dateStr).substr(1,dateStr.length).split('=')
    //return [JSON.stringify(dateStr).substr(0,19),JSON.stringify(dateStr).substr(21,19)]
  }
}

// 金额相加
pub.add = function(arg1, arg2) {
  if (isNaN(arg2)) {
    arg2 = 0
  }
  let r1, r2, m, arg1s, arg2s
  arg1s = arg1.toString().replace('.', '')
  arg2s = arg2.toString().replace('.', '')
  try { r1 = arg1.toString().split('.')[1].length } catch (e) { r1 = 0 }
  try { r2 = arg2.toString().split('.')[1].length } catch (e) { r2 = 0 }
  if (r1 !== r2) {
    var zero = ''
    for (var i = 0; i < (Math.abs(r1 - r2)); i++) {
      zero += '0'
    }
  };
  if (r1 > r2) {
    arg2s += zero
  } else if (r1 < r2){
    arg1s += zero
  }
  m = Math.pow(10, Math.max(r1, r2))
  return (Number(arg2s) + Number(arg1s)) / m
}
export default pub
