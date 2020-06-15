import axios from 'axios'
import {post, postBlob, put, patch, fetch, jsonPost, filePost} from '../utils/http'
import {baseUrl} from './apiUrl'

const channel =  {
    queryChannelList (param) {
        return fetch(baseUrl + './table.json', param)
    }
}

const API = {
    ...channel
}

export default API
