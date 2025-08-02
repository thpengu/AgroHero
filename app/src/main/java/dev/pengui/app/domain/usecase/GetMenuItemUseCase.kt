package dev.pengui.app.domain.usecase

import android.util.Log
import dev.pengui.app.data.repository.MenuRepository
import dev.pengui.app.domain.model.MenuItem

class GetMenuItemUseCase (
    private val repository: MenuRepository
) {
    suspend operator fun invoke(): List<MenuItem> {
        return repository.getMenuItems()
    }
}