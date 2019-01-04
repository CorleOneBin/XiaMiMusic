#-*- encoding=utf-8 -*-

import urllib.request
from bs4 import BeautifulSoup

class Util:

    #将url解析为soup
    def urlToSoup(url):
        resp = urllib.request.urlopen(url)
        soup =  BeautifulSoup(resp,"html.parser")
        return soup

    #将歌曲id解析为.mp3连接
    def idToMp3Url(id):
        url = "http://player.kuwo.cn/webmusic/st/getNewMuiseByRid?rid=MUSIC_%s" % str(id)
        soup = Util.urlToSoup(url)
        if soup.select("mp3path").__len__() == 0 or soup.select("mp3dl").__len__() == 0:
            return
        mp3Path = soup.select("mp3path")[0].get_text()
        mp3dl = soup.select("mp3dl")[0].get_text()
        return "%s/resource/%s" % (mp3dl,mp3Path)