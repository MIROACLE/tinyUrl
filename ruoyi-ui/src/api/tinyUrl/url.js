import request from '@/utils/request'
import { praseStrEmpty } from "@/utils/ruoyi";

// 查询公告列表
export function listTinyUrl(query) {
  return request({
    url: '/tiny/url/list',
    method: 'get',
    params: query
  })
}

// 生成短链接
export function createTinyUrl(data) {
  return request({
    url: '/tiny/url',
    method: 'post',
    data: data
  })
}

// 删除公告
export function delTinyUrl(tinyUrlId) {
  return request({
    url: '/tiny/url/' + tinyUrlId,
    method: 'delete'
  })
}

// 查看链接
export function viewUrl(data) {
  return request({
    url: '/view/'+data,
    method: 'get',
  })
}