package com.mmrx.health.bean;

import java.util.List;


public class NetNewsBean {


    /**
     * code : 200
     * msg : success
     * newslist : [{"ctime":"2019-03-1917:13","title":"医生在列车上救人的风险有多大？","description":"网易健康","picUrl":"http://imgsize.ph.126.net/?imgurl=http://cms-bucket.ws.126.net/2019/03/20/dae6c19b60cd46b0aa24e6a9a9ef08bd.jpeg_150x100x1x85.jpg","url":"http://jiankang.163.com/19/0319/17/EAL854H400388165.html"},{"ctime":"2019-03-1820:46","title":"900多家医院入选首批分娩镇痛试点","description":"网易健康","picUrl":"http://imgsize.ph.126.net/?imgurl=http://cms-bucket.ws.126.net/2019/03/19/e825741b726a42378d9dbba456380d59.jpeg_150x100x1x85.jpg","url":"http://jiankang.163.com/19/0318/20/EAJ1TQK800388165.html"},{"ctime":"2019-03-1807:30","title":"中国超六成青少年儿童睡眠时间不足8小时","description":"网易健康","picUrl":"http://imgsize.ph.126.net/?imgurl=http://cms-bucket.ws.126.net/2019/03/18/7e692bb105c9457995f46c0187bf5773.jpeg_150x100x1x85.jpg","url":"http://jiankang.163.com/19/0318/09/EAHPSDH100388165.html"}]
     */

    private int code;
    private String msg;
    private List<NewslistBean> newslist;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<NewslistBean> getNewslist() {
        return newslist;
    }

    public void setNewslist(List<NewslistBean> newslist) {
        this.newslist = newslist;
    }

    public static class NewslistBean {
        /**
         * ctime : 2019-03-1917:13
         * title : 医生在列车上救人的风险有多大？
         * description : 网易健康
         * picUrl : http://imgsize.ph.126.net/?imgurl=http://cms-bucket.ws.126.net/2019/03/20/dae6c19b60cd46b0aa24e6a9a9ef08bd.jpeg_150x100x1x85.jpg
         * url : http://jiankang.163.com/19/0319/17/EAL854H400388165.html
         */

        private String ctime;
        private String title;
        private String description;
        private String picUrl;
        private String url;

        public String getCtime() {
            return ctime;
        }

        public void setCtime(String ctime) {
            this.ctime = ctime;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getPicUrl() {
            return picUrl;
        }

        public void setPicUrl(String picUrl) {
            this.picUrl = picUrl;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
