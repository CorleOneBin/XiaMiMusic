# -*- encoding=utf-8 -*-


import urllib.request
from bs4 import BeautifulSoup

resp =  urllib.request.urlopen("http://localhost:8080/cinfo/toMusicList?cinfoId=1515")
pageBytes = resp.read()
pageHtml = pageBytes.decode("utf-8")
print(pageHtml)