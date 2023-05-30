#!/bin/sh

# 复制项目的文件到对应docker路径，便于一键生成镜像。
usage() {
	echo "Usage: sh copy.sh"
	exit 1
}


# copy sql
echo "begin copy sql "
cp ../sql/ry_20220814.sql ./mysql/db
cp ../sql/ry_config_20220510.sql ./mysql/db

# copy html
echo "begin copy html "
cp -r ../yyy-ui/dist/** ./nginx/html/dist


# copy jar
echo "begin copy yyy-gateway "
cp ../yyy-gateway/target/yyy-gateway.jar ./ruoyi/gateway/jar

echo "begin copy yyy-auth "
cp ../yyy-auth/target/yyy-auth.jar ./ruoyi/auth/jar

echo "begin copy yyy-visual "
cp ../yyy-visual/yyy-monitor/target/yyy-visual-monitor.jar  ./ruoyi/visual/monitor/jar

echo "begin copy yyy-modules-system "
cp ../yyy-modules/yyy-system/target/yyy-modules-system.jar ./ruoyi/modules/system/jar

echo "begin copy yyy-modules-file "
cp ../yyy-modules/yyy-file/target/yyy-modules-file.jar ./ruoyi/modules/file/jar

echo "begin copy yyy-modules-job "
cp ../yyy-modules/yyy-job/target/yyy-modules-job.jar ./ruoyi/modules/job/jar

echo "begin copy yyy-modules-gen "
cp ../yyy-modules/yyy-gen/target/yyy-modules-gen.jar ./ruoyi/modules/gen/jar

