package dev.pengui.app.data.repository.impl

import dev.pengui.app.data.repository.MenuRepository
import dev.pengui.app.domain.model.MenuItem
import dev.pengui.R

class MenuRepoImpl: MenuRepository {
    override suspend fun getMenuItems(): List<MenuItem> {
        // Fetch from API/local DB
        return listOf(
            MenuItem("1", "Shop", "shop_icon", R.drawable.ic_shop),
            MenuItem("2", "Plants", "plant_icon", R.drawable.ic_shop)
        )
    }
}