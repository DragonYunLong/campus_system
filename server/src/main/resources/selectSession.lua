local session = redis.call("get",KEYS[1])
return session;