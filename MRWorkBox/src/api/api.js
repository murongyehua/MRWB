import axios from 'axios'
import {post, postBlob, put, patch, fetch, jsonPost, filePost} from '../utils/http'
import {baseUrl} from './apiUrl'

let url = baseUrl + '/mr'

const user =  {
    userLogin (param) {
        return post(url + '/base/userInfo/login.pub', param)
    }
}

const right = {
    getMenu (param) {
        return post(url + '/base/menu/getMenu.do', param)
    }
}

const API = {
    ...user,
    ...right
}

export default API
