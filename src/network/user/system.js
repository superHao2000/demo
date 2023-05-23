import {request} from "../request";

// 定义新的文件进行引用,单独管理文件
export function getAllAdminHandle(pageNum, pageSize) {
    return request({
        method: "get",
        url: `/admin/getAllAdmin/${pageNum}/${pageSize}`
    });
}

export function deleteUserHandle(aid) {
    return request({
        method: "post",
        url: "/admin/deleteOne",
        data: {
            aid
        }
    });
}
export function updateUserHandle(admin) {
    return request({
        method: "post",
        url: "/admin/update",
        data: admin
    });
}
