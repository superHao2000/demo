import {request} from "../request";

// 定义新的文件进行引用,单独管理文件
export function getAllEmploymentHandle(userId, type, pageNum, pageSize, str) {
    return request({
        method: "get",
        url: `/employment/getAllEmployment/${pageNum}/${pageSize}`,
        params: {
            userId,
            type,
            str,
        },
    });
}

export function updateEmploymentHandle(employment) {
    return request({
        method: "post",
        url: "/employment/update",
        data: employment
    });
}

export function AddEmploymentHandle(employment) {
    return request({
        method: "post",
        url: "/employment/save",
        data:
        employment,
    });
}

export function deleteEmploymentHandle(employment) {
    return request({
        method: "post",
        url: "/employment/delete",
        data: employment
    });
}

export function delAllEmploymentIpserver(arr) {
    return request({
        method: "post",
        url: "/user/deleteAll",
        data: arr
    });
}
