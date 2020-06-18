import axios from 'axios'
import {post, postBlob, put, patch, fetch, jsonPost, filePost} from '../utils/http'
import {baseUrl} from './apiUrl'

let url = baseUrl + '/mr'

const channel =  {
    userLogin (param) {
        return post(url + '/base/userInfo/login.pub', param)
    }
}

const API = {
    ...channel
}

export default API
