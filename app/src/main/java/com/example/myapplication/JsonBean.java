package com.example.myapplication;

import java.util.List;

public class JsonBean {


    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * listPosition : 0
         * sectionPosition : 0
         * text : A
         * type : 1
         * listPosition  : 121
         */

        private int listPosition;
        private int sectionPosition;
        private String text;
        private int type;


        public int getSectionPosition() {
            return sectionPosition;
        }

        public void setSectionPosition(int sectionPosition) {
            this.sectionPosition = sectionPosition;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getListPosition() {
            return listPosition;
        }

        public void setListPosition(int listPosition) {
            this.listPosition = listPosition;
        }
    }
}
