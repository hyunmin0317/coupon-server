# Redis 데이터 구조 학습

## String

String 타입의 데이터 구조로 텍스트와 직렬화된 객체 등을 저장하는 용도로 자주 사용된다.

### SET

**Time complexity:** O(1)
**Description:** 지정된 key의 저장된 문자열을 저장

```redis
SET key value
```

```java
public void set(String key, String value) {
    redisTemplate.opsForValue().set(key, value);
}
```

## GET

**Time complexity:** O(1)
**Description:** 지정된 key의 저장된 문자열을 조회

```redis
GET key
```

```java
public String get(String key) {
    return redisTemplate.opsForValue().get(key);
}
```

## List

데이터의 삽입 순서에 따라 정렬된 문자열 컬렉션의 형태

### LRANGE

**Time complexity:** O(S+N) - S는 HEAD or TAIL distance, N은 지정된 요소 수
**Description:** List에서 지정된 범위의 요소를 반환

```redis
LRANGE key start stop
```

```java
public List<String> lrange(String key, long start, long end) {
    return redisTemplate.opsForList().range(key, start, end);
}
```

### LPUSH

**Time complexity:** O(1)
**Description:** List의 Head에 지정된 요소를 추가

-> **0**00000

```redis
LPUSH key element
```

```java
public Long lpush(String key, String value) {
    return redisTemplate.opsForList().leftPush(key, value);
}
```

### RPUSH

**Time complexity:** O(1)
**Description:** List의 Tail에 지정된 요소를 추가

-> 00000**0**

```redis
RPUSH key element
```

```java
public Long rpush(String key, String value) {
    return redisTemplate.opsForList().rightPush(key, value);
}
```

### LPOP

**Time complexity:** O(N) - N은 반환된 요소의 수
**Description:** List의 첫 번째 요소를 제거하고 반환

```redis
LPOP key [count]
```

```java
public String lpop(String key) {
    return redisTemplate.opsForList().leftPop(key);
}
```

### RPOP

**Time complexity:** O(N) - N은 반환된 요소의 수
**Description:** List의 마지막 요소를 제거하고 반환

```redis
RPOP key [count]
```

```java
public String rpop(String key) {
    return redisTemplate.opsForList().rightPop(key);
}
```

### LLEN

**Time complexity:** O(1)
**Description:** List에 저장된 요소 수를 반환

```redis
LLEN key
```

```java
public Long llen(String key) {
    return redisTemplate.opsForList().size(key);
}
```

### LPOS

**Time complexity:** O(N) - N은 목록의 요소 수
**Description:** List에서 일치하는 요소를 찾고 인덱스를 반환

```redis
LPOS key element
```

```java
public Long lpos(String key, String element) {
    return redisTemplate.execute((connection) -> connection.lPos(key.getBytes(), element.getBytes()));
}
```

## SET

순서가 지정되지 않은 문자열 컬렉션의 형태로 중복 요소가 허용되지 않는다

### SMEMBERS

**Time complexity:** O(N) - N은 저장된 요소의 수
**Description:** Set에 저장된 모든 요소를 반환

```redis
SMEMBERS key
```

```java
public Set<Object> smembers(String key){
    return redisTemplate.opsForSet().members(key);
}
```

### SADD

**Time complexity:** O(1)
**Description:** Set에 멤버를 추가

```redis
SADD key member
```

```java
public Long sadd(String key, Object... members){
    return redisTemplate.opsForSet().add(key,members);
}
```

### SISMEMBER

**Time complexity:** O(1)
**Description:** Set에 멤버가 포함되어 있는지 확인

```redis
SISMEMBER key member (있으면 1, 없으면 0 반환)
```

```java
public Boolean sismember(String key, Object member){
    return redisTemplate.opsForSet().isMember(key,member);
}
```

### SCARD

**Time complexity:** O(1)
**Description:** Set에 저장된 요소 수를 반환

```redis
SCARD key
```

```java
public Long sCard(String key) {
    return redisTemplate.opsForSet().size(key);
}
```

### SREM

**Time complexity:** O(N) - N은 삭제할 요소의 수
**Description:** Set에 저장된 요소를 제거

```redis
SREM key member
```

```java
public Long sRem(String key, String value) {
    return redisTemplate.opsForSet().remove(key, value);
}
```

## Sorted Set

지정한 스코어에 따라서 순서가 지정되는 문자열 컬렉션의 형태. 중복 요소가 허용되지 않음.

### ZRANGE

**Time complexity:** O(log(N)+M) - N은 정렬된 집합의 요소 수, M은 반환된 요소 수
**Description:** Sorted Set에 저장된 범위 내(순위, 스코어) 요소를 반환

```redis
ZRANGE key start stop
```

```java
public Set<String> zRange(String key, long start, long end) {
    return redisTemplate.opsForZSet().range(key, start, end);
}
```

### ZADD

**Time complexity:** O(log(N)) - N은 정렬된 집합의 요소 수
**Description:** member가 score에 의해 정렬 및 저장. 동일한 score인 경우 사전순 정렬

```redis
ZADD key score member
```

```java
public Boolean zAdd(String key, String value, double score) {
    return redisTemplate.opsForZSet().add(key, value, score);
}
```

### ZCARD

**Time complexity:** O(1)
**Description:** 해당하는 key의 요소 수를 반환

```redis
ZCARD key
```

```java
public Long zCard(String key) {
    return redisTemplate.opsForZSet().size(key);
}
```

### ZPOPMIN

**Time complexity:** O(log(N)*M) - N은 정렬된 집합의 요소 수, M은 POP 된 요소 수
**Description:** 점수가 낮은 순으로 멤버를 제거하고 반환

```redis
ZRANGE key start stop
```

```java
public String zPopMin(String key) {
    return redisTemplate.opsForZSet().removeRangeByScore(key, Double.MIN_VALUE, Double.MAX_VALUE);
}
```

### ZPOPMAX

**Time complexity:** O(log(N)*M) - N은 정렬된 집합의 요소 수, M은 POP 된 요소 수
**Description:** 점수가 높은 순으로 멤버를 제거하고 반환

```redis
ZPOPMAX key [count]
```

```java
public String zPopMax(String key) {
    return redisTemplate.opsForZSet().removeRangeByScore(key, Double.MIN_VALUE, Double.MAX_VALUE);
}
```
