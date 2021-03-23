red='\033[0;31m'
green='\033[0;32m'
yellow='\033[0;33m'
blue='\033[0;34m'
purple='\033[0;35m'
cyan='\033[0;36m'
white='\033[0;37m'
end='\033[0m'

help(){
    printf "Run：$red sh $0 $green<verb> $yellow<args>$end\n"
    format="  $green%-6s $yellow%-8s$end%-20s\n"
    printf "$format" "-h" "" "帮助"
}

login(){
    curl -H "Content-Type: application/json" -X POST -d '{"username":"'$1'","password":"123456"}' http://127.0.0.1:8889/login | sed 's/.*result":"//g' | sed 's/\".*//g' > token.log
}

request(){
    token=$(cat token.log)
    curl -H "Content-Type: application/json" -H "Authorization: CustomPrefix $token" http://127.0.0.1:8889$1
}

case $1 in 
    -h)
        help ;;
    -A)
        login 'admin'
    ;;
    -U)
        login 'user'
    ;;
    -l)
        request '/list'
    ;;
    -u)
        request '/user'
    ;;
    -a)
        request '/admin'
    ;;
    *)
        help ;;
esac







