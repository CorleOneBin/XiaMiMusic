# -*- encoding=utf-8 -*-


#歌单种类
class Category:

    def __init__(self, id, kind, href, name):
        self.id = id
        self.kind = kind
        self.href = href
        self.name = name