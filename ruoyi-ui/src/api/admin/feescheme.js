import request from '@/utils/request'

// 查询收费方案管理列表
export function listFeescheme(query) {
  return request({
    url: '/admin/feescheme/list',
    method: 'get',
    params: query
  })
}

// 查询收费方案管理详细
export function getFeescheme(fsmid) {
  return request({
    url: '/admin/feescheme/' + fsmid,
    method: 'get'
  })
}

// 新增收费方案管理
export function addFeescheme(data) {
  return request({
    url: '/admin/feescheme',
    method: 'post',
    data: data
  })
}

// 修改收费方案管理
export function updateFeescheme(data) {
  return request({
    url: '/admin/feescheme',
    method: 'put',
    data: data
  })
}

// 删除收费方案管理
export function delFeescheme(fsmid) {
  return request({
    url: '/admin/feescheme/' + fsmid,
    method: 'delete'
  })
}
