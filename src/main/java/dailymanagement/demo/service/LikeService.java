package dailymanagement.demo.service;

import dailymanagement.demo.utils.RedisKeyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SessionCallback;
import org.springframework.stereotype.Service;

@Service
public class LikeService {

    @Autowired
    private RedisTemplate redisTemplate;

    // 点赞
    public void like(int userId, int blogId, int model) {
        redisTemplate.execute(new SessionCallback() {
            @Override
            public Object execute(RedisOperations operations) throws DataAccessException {
                String entityLikeKey = RedisKeyUtil.getEntityLikeKey(1, blogId);
//                String userLikeKey = RedisKeyUtil.getUserLikeKey(entityUserId);
//                boolean isMember = operations.opsForSet().isMember(entityLikeKey, userId);
                operations.multi();
                if (model == -1) {
                    operations.opsForSet().remove(entityLikeKey, userId);
//                    operations.opsForValue().decrement(userLikeKey);
                } else if (model == 1) {
                    operations.opsForSet().add(entityLikeKey, userId);
//                    operations.opsForValue().increment(userLikeKey);
                }
                return operations.exec();
            }
        });
    }

    // 查询某博客点赞的数量
    public long findEntityLikeCount(int blogId) {
        String entityLikeKey = RedisKeyUtil.getEntityLikeKey(1, blogId);
        return redisTemplate.opsForSet().size(entityLikeKey);
    }

    // 查询某人对博客的点赞状态
    public int findEntityLikeStatus(int userId, int blogId) {
        String entityLikeKey = RedisKeyUtil.getEntityLikeKey(1, blogId);
        return redisTemplate.opsForSet().isMember(entityLikeKey, userId) ? 1 : 0;
    }
}
