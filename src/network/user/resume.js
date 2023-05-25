import {request} from "../request";

// 定义新的文件进行引用,单独管理文件
export function getAllResumeHandle(userId, type, pageNum, pageSize, str) {
    return request({
        method: "get",
        url: `/resume/getAllResume/${pageNum}/${pageSize}`,
        params: {
            userId,
            type,
            str,
        },
    });
}

export function updateResumeHandle(resume) {
    return request({
        method: "post",
        url: "/resume/update",
        data: resume
    });
}

export function AddResumeHandle(resume) {
    return request({
        method: "post",
        url: "/resume/save",
        data:
        resume,
    });
}

export function deleteResumeHandle(resume) {
    return request({
        method: "post",
        url: "/resume/delete",
        data: resume
    });
}

export function delAllResumeIpserver(arr) {
    return request({
        method: "post",
        url: "/user/deleteAll",
        data: arr
    });
}
