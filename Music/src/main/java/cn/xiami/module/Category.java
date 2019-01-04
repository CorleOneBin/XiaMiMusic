package cn.xiami.module;

import java.util.List;

/**
 * 歌单的种类,例如  网络，DJ,经典
 */
public class Category {

    private Integer id;
    private String name;
    //判断是特色分类还是热门分类
    private Integer kind;
    private String href;
    //这个种类下包含哪些歌单
    private List<Cinfo> cinfos;

    public Category() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getKind() {
        return kind;
    }

    public void setKind(Integer kind) {
        this.kind = kind;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public List<Cinfo> getCinfos() {
        return cinfos;
    }

    public void setCinfos(List<Cinfo> cinfos) {
        this.cinfos = cinfos;
    }
}
