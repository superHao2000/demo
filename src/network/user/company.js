import {request} from "../request";

export function getAllCompanyHandle(pageNum, pageSize, str) {
    return request({
        method: "get",
        url: `/company/getAllCompany/${pageNum}/${pageSize}`,
        params: {str}
    });
}

export function deleteCompanyHandle(sid) {
    return request({
        method: "post",
        url: "/company/deleteOne",
        data: {
            sid
        }
    });
}

export function updateCompanyHandle(company) {
    return request({
        method: "post",
        url: "/company/update",
        data: company
    });
}
