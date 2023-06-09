#!/bin/sh

# 复制项目的文件到对应docker路径，便于一键生成镜像。
usage() {
	echo "Usage: sh copy.sh"
	exit 1
}


# copy sql
echo "begin copy sql "
cp ../sql/yyy_20220814.sql ./mysql/db
cp ../sql/yyy_config_20220929.sql ./mysql/db

# copy html
echo "begin copy html "
cp -r ../yyy-ui/dist/** ./nginx/html/dist


# copy jar
echo "begin copy yyy-gateway "
cp ../yyy-gateway/target/yzz-gateway.jar ./yyy/gateway/jar

echo "begin copy yyy-auth "
cp ../yyy-auth/target/yzz-auth.jar ./yyy/auth/jar

echo "begin copy yyy-visual "
cp ../yyy-visual/yyy-monitor/target/yzz-visual-monitor.jar  ./yyy/visual/monitor/jar

echo "begin copy yyy-modules-system "
cp ../yyy-modules/yyy-system/target/yzz-modules-system.jar ./yyy/modules/system/jar

#echo "begin copy yyy-modules-file "
#cp ../yyy-modules/yyy-file/target/yyy-modules-file.jar ./yyy/modules/file/jar

echo "begin copy yyy-modules-job "
cp ../yyy-modules/yyy-job/target/yzz-modules-job.jar ./yyy/modules/job/jar

echo "begin copy yyy-modules-gen "
cp ../yyy-modules/yyy-gen/target/yzz-modules-gen.jar ./yyy/modules/gen/jar

