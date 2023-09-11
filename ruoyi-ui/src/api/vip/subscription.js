import request from '@/utils/request'

// 查询会员订阅信息列表
export function listSubscription(query) {
  return request({
    url: '/vip/subscription/list',
    method: 'get',
    params: query
  })
}

// 查询会员订阅信息详细
export function getSubscription(id) {
  return request({
    url: '/vip/subscription/' + id,
    method: 'get'
  })
}

// 新增会员订阅信息
export function addSubscription(data) {
  return request({
    url: '/vip/subscription',
    method: 'post',
    data: data
  })
}

// 修改会员订阅信息
export function updateSubscription(data) {
  return request({
    url: '/vip/subscription',
    method: 'put',
    data: data
  })
}

// 删除会员订阅信息
export function delSubscription(id) {
  return request({
    url: '/vip/subscription/' + id,
    method: 'delete'
  })
}
