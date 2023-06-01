import {request} from "../request";

// 定义新的文件进行引用,单独管理文件
export function getAllRecruitmentHandle(userId, type, pageNum, pageSize, str) {
    return request({
        method: "get",
        url: `/recruitment/getAllRecruitment/${pageNum}/${pageSize}`,
        params: {
            userId,
            type,
            str,
        },
    });
}

export function updateRecruitmentHandle(recruitment) {
    return request({
        method: "post",
        url: "/recruitment/update",
        data: recruitment
    });
}

export function AddRecruitmentHandle(recruitment) {
    return request({
        method: "post",
        url: "/recruitment/save",
        data:
        recruitment,
    });
}

export function deleteRecruitmentHandle(recruitment) {
    return request({
        method: "post",
        url: "/recruitment/delete",
        data: recruitment
    });
}

export function delAllRecruitmentIpserver(arr) {
    return request({
        method: "post",
        url: "/user/deleteAll",
        data: arr
    });
}

export function addDelivery(recruitmentId, resumeId) {
    return request({
        method: "post",
        url: "/delivery/add",
        data: {
            recruitmentId, resumeId
        }
    });
}
