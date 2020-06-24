import axios from 'axios'
import {post, postBlob, put, patch, fetch, jsonPost, filePost} from '../utils/http'
import {baseUrl} from './apiUrl'

let url = baseUrl + '/mr'

const user =  {
    userLogin (param) {
        return post(url + '/base/userInfo/login.pub', param)
    },
    queryUserList (param) {
        return post(url + '/base/userInfo/query.smg', param)
    },
    addUser (param) {
        return post(url + '/base/userInfo/add.smg', param)
    }
}

const right = {
    getMenu (param) {
        return post(url + '/base/menu/getMenu.do', param)
    }
}

const project = {
    queryProjectList (param) {
        return post(url + '/base/project/query.pub', param)
    }
}

const API = {
    ...user,
    ...right,
    ...project
}

export default API
