package com.hgs.zjcp.data;

import java.io.Serializable;
import java.util.Arrays;

/**
 * Created by hgs on 2016/10/29.
 * Mob 的菜单分类
 */
public class MobMenuCategory {
    private String name;
    private Child[] childs;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Child[] getChilds() {
        return childs;
    }

    public void setChilds(Child[] childs) {
        this.childs = childs;
    }

    @Override
    public String toString() {
        return "MobMenuCategory{" +
                "name='" + name + '\'' +
                ", childs=" + Arrays.toString(childs) +
                '}';
    }

    /**
     * Created by hgs on 2016/10/29.
     * Mob的菜单分类封装子项
     */
    public class Child implements Serializable{
        public static final long suid = 20162308;
        private String ctgId;
        private String name;
        private String parentId;

        public String getCtgId() {
            return ctgId;
        }

        public void setCtgId(String ctgId) {
            this.ctgId = ctgId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getParentId() {
            return parentId;
        }

        public void setParentId(String parentId) {
            this.parentId = parentId;
        }

        @Override
        public String toString() {
            return "MobMenuChilds{" +
                    "ctgId='" + ctgId + '\'' +
                    ", name='" + name + '\'' +
                    ", parentId='" + parentId + '\'' +
                    '}';
        }
    }
}
