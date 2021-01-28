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
    },
    editUser (param) {
        return post(url + '/base/userInfo/edit.do', param)
    },
    resetPassword (param) {
        return post(url + '/base/userInfo/resetPassword.smg', param)
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
    },
    addUserRight (param) {
        return post(url + '/base/userRight/addUserRight.smg', param)
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
    },
    getModalsByProject (param){
        return post(url + '/base/modal/getModalsByProject.smg', param)
    }
}

const menu = {
    queryMenuList (param) {
        return post(url + '/base/menu/query.smg', param)
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
    editJournalTag (param) {
        return post(url + '/journal/tag/edit.do', param)
    },
    addJournalSummary (param) {
        return jsonPost(url + '/journal/summary/add.do', param)
    },
    queryJournalSummary (param) {
        return post(url + '/journal/summary/query.do', param)
    },
    getJournalSummaryById (param) {
        return post(url + '/journal/summary/getById.do', param)
    },
    editJournalSummary (param) {
        return jsonPost(url + '/journal/summary/edit.do', param)
    },
    queryJournalSummaryHistory (param) {
        return post(url + '/journal/summary/queryHistory.do', param)
    },
    deleteJournalSummary (param) {
        return jsonPost(url + '/journal/summary/delete.do', param)
    },
    exportJournalSummaryURL (param) {
        return postBlob(url + '/journal/summary/export.pub', param)
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
