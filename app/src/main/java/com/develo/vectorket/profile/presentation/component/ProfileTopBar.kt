package com.develo.vectorket.profile.presentation.component

import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import com.develo.vectorket.core.presentation.util.MoreVertIcon

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileTopBar(
    title: String = "Profile",
    onLogOutClick: () -> Unit
) {
    var openMenu by rememberSaveable {
        mutableStateOf(false)
    }

    TopAppBar(
        title = {
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium
            )
        },
        actions = {
            IconButton(onClick = { openMenu = !openMenu }) {
                MoreVertIcon()
            }
            DropdownMenu(
                expanded = openMenu,
                onDismissRequest = { openMenu = !openMenu }
            ) {
                DropdownMenuItem(
                    text = {
                        Text(
                            text = "Log out",
                            style = MaterialTheme.typography.bodyMedium
                        )
                    },
                    onClick = onLogOutClick
                )
            }
        }
    )
}