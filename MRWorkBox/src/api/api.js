import axios from 'axios'
import {post, postBlob, put, patch, fetch, jsonPost, filePost} from '../utils/http'
import {baseUrl} from './apiUrl'
import {parseMinWidth} from "element-ui/packages/table/src/util";

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
    },
    getProject (param) {
        return post(url + '/base/userRight/getProjects.do', param)
    },
    getUsers (param) {
        return post(url + '/base/userRight/getUsers.do', param)
    }
}

const project = {
    queryProjectList (param) {
        return post(url + '/base/project/query.pub', param)
    }
}

const modal = {
    queryModalList (param) {
        return post(url + '/base/modal/query.smg', param)
    }
}

const menu = {
    queryMenuList (param) {
        return post(url + '/base/menu.query.smg', param)
    }
}

const journal = {
    getJournalFields (param) {
        return post(url + '/journal/field/getFields.do', param)
    },
    addJournalTag (param) {
        return post(url + '/journal/tag/add.do', param)
    },
    queryJournalTag (param) {
        return post(url + '/journal/tag/query.do', param)
    },
    addJournalSummary (param) {
        return jsonPost(url + '/journal/summary/add.do', param)
    }
}

const API = {
    ...user,
    ...right,
    ...project,
    ...modal,
    ...menu,
    ...journal
}

export default API
