# -*- encoding=utf-8 -*-

import urllib.request
from bs4 import BeautifulSoup

from Dao import Dao
from MyUtil import Util


# 歌单种类的爬取======================================
class CateParse:

    def __init__(self):
        self.url = "http://yinyue.kuwo.cn/yy/cate_27.htm"
        self.soup = Util.urlToSoup(self.url)

    # 解析连接
    def parse(self):
        hotlist = self.soup.find_all("div", class_="hotlist")
        dao = Dao()

        # 获取热门分类
        listA = hotlist[0].select("a")
        for a in listA:
            name = a.get_text()
            href = a.get("href")
            id = href[9:][:-4]
            kind = 1
            sql = 'insert ignore into tb_category (id,kind,href,name) values (%d,%d,"%s","%s" )' \
                  % (int(id), int(kind), str("http://yinyue.kuwo.cn" + href), str(name))
            dao.executeSql(sql)
            print("id=%d,name=%s,href=%s" % (int(id), name, href))

        # 获取特色分类
        listA = hotlist[1].select("a")
        for a in listA:
            name = a.get_text()
            href = a.get("href")
            id = href[9:][:-4]
            kind = 2
            sql = 'insert ignore into tb_category (id,kind,href,name) values (%d,%d,"%s","%s" )' \
                  % (int(id), int(kind), str("http://yinyue.kuwo.cn" + href), str(name))
            dao.executeSql(sql)
            print("id=%d,name=%s,href=%s" % (int(id), name, href))


# 歌单的爬取===========================================
class CinfoParse:

    def parse(self):
        dao = Dao()
        sql = 'select href,id from tb_category'
        # 获取歌单种类的href
        res = dao.executeQuerySql(sql)
        for r in res:
            cateHref = r[0]
            cateId = r[1]
            soup = Util.urlToSoup(cateHref)
            ul = soup.find_all("ul", class_="singer_list")
            lis = ul[0].select("li")
            for li in lis:
                href = li.select("a")[0].get("href")
                tmp = href[4:8]
                # 如果还是歌单种类的话，就忽略
                if tmp == "cate":
                    continue
                name = li.select("a")[0].get("title")
                imgHref = li.select("a")[0].select("img")[0].get("lazy_src")
                num = li.select("p[class=m_number]")[0].get_text()[:-3]
                # 忽略有些没有歌曲数量的歌单
                if not num.isdigit():
                    continue
                id = href[10:-4]
                #插入歌单信息
                sql = 'insert ignore into tb_cinfo (id,href,imgHref,name,num) values (%d,"%s","%s","%s",%d)' % (
                    int(id), "http://yinyue.kuwo.cn" + href, imgHref, name, int(num))
                #插入种类与歌单的对应信息
                sql1 = 'insert ignore into cate_cinfo (cate_id,cinfo_id) values (%d,%d)' % (int(cateId), int(id))
                dao.executeSql(sql)
                dao.executeSql(sql1)
                print("id=%d,name=%s,num=%d, imgHref=%s ,   href=%s ,   cateHref=%s" % (
                    int(id), name, int(num), imgHref, str("http://yinyue.kuwo.cn" + href), cateHref))

#歌曲的爬取
class MusicParse:

    def parse(self):
        dao = Dao()
        sql = "select id , href from tb_cinfo"
        res = dao.executeQuerySql(sql)
        for r in res:
            cinfoId = r[0]
            cinfoHref=r[1]
            soup = Util.urlToSoup(cinfoHref)
            #获取上层歌单的描述信息
            cinfoDesc = soup.select("#intro")[0].get_text()
            sql = 'insert ignore into tb_cinfo (descri) values ("%s")' % (cinfoDesc)
            dao.executeSql(sql)
            #获取歌曲的信息
            musicList = soup.find(id="musicList")
            lis = musicList.select("li")
            for li in lis:
                li.select("p")
                musicName = li.select("p[class=m_name]")[0].select("a")[0].get("title")
                songer = li.select("p[class=s_name]")[0].select("a")[0].get("title")
                al = li.select("p[class=a_name]")[0].select("a")
                if al.__len__() == 0:
                    album = None
                else:
                    album = li.select("p[class=a_name]")[0].select("a")[0].get("title")
                musicHref = li.select("p[class=m_name]")[0].select("a")[0].get("href")
                musicId = musicHref[26:-1]
                mp3Url = Util.idToMp3Url(int(musicId))
                #插入歌曲信息
                sql = 'insert ignore into tb_music (id,name,songer,album,href,mp3) values(%d,"%s","%s","%s","%s","%s")' % (int(musicId),musicName,songer,album,musicHref,mp3Url)
                #插入歌曲与cinfo关联信息
                sql1 = 'insert ignore into cinfo_music (cinfo_id,music_id) values (%d,%d)' % (int(cinfoId),int(musicId))
                dao.executeSql(sql)
                dao.executeSql(sql1)
                print("id=%d,name=%s,songer=%s,  album=%s, cinfoHref=%s  musicHref=%s,  mp3=%s" % (int(musicId),musicName,songer,album,cinfoHref,musicHref,mp3Url))


MusicParse().parse()

