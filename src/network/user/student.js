import {request} from "../request";

export function getAllStudentHandle(pageNum, pageSize, str) {
    return request({
        method: "get",
        url: `/student/getAllStudent/${pageNum}/${pageSize}`,
        params: {str}
    });
}

export function deleteStudentHandle(sid) {
    return request({
        method: "post",
        url: "/student/deleteOne",
        data: {
            sid
        }
    });
}

export function updateStudentHandle(student) {
    return request({
        method: "post",
        url: "/student/update",
        data: student
    });
}
