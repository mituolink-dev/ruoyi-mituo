import request from '@/utils/request'

// 查询会员内购信息列表
export function listProducts(query) {
  return request({
    url: '/vip/products/list',
    method: 'get',
    params: query
  })
}

// 查询会员内购信息详细
export function getProducts(id) {
  return request({
    url: '/vip/products/' + id,
    method: 'get'
  })
}

// 新增会员内购信息
export function addProducts(data) {
  return request({
    url: '/vip/products',
    method: 'post',
    data: data
  })
}

// 修改会员内购信息
export function updateProducts(data) {
  return request({
    url: '/vip/products',
    method: 'put',
    data: data
  })
}

// 删除会员内购信息
export function delProducts(id) {
  return request({
    url: '/vip/products/' + id,
    method: 'delete'
  })
}
