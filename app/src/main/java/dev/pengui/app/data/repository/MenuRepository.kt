package dev.pengui.app.data.repository

import dev.pengui.app.domain.model.MenuItem

interface MenuRepository {
    suspend fun getMenuItems(): List<MenuItem>
}