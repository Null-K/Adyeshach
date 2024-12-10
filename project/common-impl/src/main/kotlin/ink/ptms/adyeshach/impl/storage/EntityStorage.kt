package ink.ptms.adyeshach.impl.storage

import ink.ptms.adyeshach.core.AdyeshachSettings
import taboolib.common.env.RuntimeDependency
import taboolib.common.util.resettableLazy

/**
 * Database:
 *   # LOCAL, MONGODB, DISABLE
 *   method: DISABLE
 *   url:
 *     client: 'mongodb://localhost:3307'
 *     database: test
 *     collection: adyeshach
 *
 * @author 坏黑
 * @since 2023/1/10 20:09
 */
@Deprecated("1.0 保留功能")
object EntityStorage {

    /** 储存方式 */
    val databaseType by resettableLazy {
        AdyeshachSettings.conf.getString("Database.method", "DISABLE")!!.uppercase()
    }

    /** 数据库地址 */
    val databaseUrl by resettableLazy {
        AdyeshachSettings.conf.getConfigurationSection("Database.url")
    }

    /** 数据库实例 */
    val database by resettableLazy {
        when (databaseType) {
            "LOCAL" -> EntityStoreSourceLocal()
            else -> EntityStoreSourceNull()
        }
    }

    /** 是否启用 */
    fun isEnabled(): Boolean {
        return database !is EntityStoreSourceNull
    }
}