# -*- encoding=utf-8 -*-

import pymysql

class Dao:

    #执行方法
    def executeSql(self,sql):
        conn = pymysql.connect("localhost", "root", "zhubin", "xiami")
        try:
            cur = conn.cursor()
            cur.execute(sql)
            conn.commit()
        except Exception:
            print(Exception)
            print("执行错误，回滚")
            conn.rollback()
        finally:
            conn.close()
            cur.close()

    #查询方法
    def executeQuerySql(self,sql):
        conn = pymysql.connect("localhost", "root", "zhubin", "xiami")
        try:
            cur = conn.cursor()
            cur.execute(sql)
            res = cur.fetchall()
            return res
        except Exception:
            print(Exception)
            print("执行错误，回滚")
            conn.rollback()
        finally:
            conn.close()
            cur.close()

