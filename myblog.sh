#!/bin/bash
# author: 13
# date: 2017-06-30


env_args="-Xms128m -Xmx128m"
sleeptime=0
arglen=$#

# get myblog pid
getpid(){
    pname="`find .. -name 'myblog*.jar'`"
    pname=${pname:3}
    pid=`ps -ef | grep $pname | grep -v grep | awk '{print $2}'`
    echo "$pid"
}

startup(){

    #构建并启动
    #mvn clean install -Dmaven.test.skip=true
    pid=$(getpid)
    if [ "$pid" != "" ]
    then
        echo "Blog already startup!"
    else
        jar_path=`find .. -name 'myblog*.jar'`
        echo "jarfile=$jar_path"
        cmd="java $1 -jar $jar_path > ./myblog.out < /dev/null &"
        echo "cmd: $cmd"
        java $1 -jar $jar_path > ./myblog.out < /dev/null &
        showlog
    fi
}

shutdown(){
    pid=$(getpid)
    if [ "$pid" != "" ]
    then
        kill -9 $pid
        echo "Blog is stop!"
    else
        echo "Blog already stop!"
    fi
}

showlog(){
    tail -f myblog.out
}

showhelp(){
    echo -e "\r\n\t欢迎使用Blog"
    echo -e "\r\nUsage: sh myblog.sh start|stop|reload|status|log"
    exit
}

showstatus(){
    pid=$(getpid)
    if [ "$pid" != "" ]
    then
        echo "Blog is running with pid: $pid"
    else
        echo "Blog is stop!"
    fi
}

if [ $arglen -eq 0 ]
 then
    showhelp
else
    if [ "$2" != "" ]
    then
        env_args="$2"
    fi
    case "$1" in
        "start")
            startup "$env_args"
            ;;
        "stop")
            shutdown
            ;;
        "reload")
            echo "reload"
            ;;
        "status")
            showstatus
            ;;
        "log")
            showlog
            ;;
    esac
fi