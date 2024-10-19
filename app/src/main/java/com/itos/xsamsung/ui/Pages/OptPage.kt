package com.itos.xsamsung

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.itos.xsamsung.XSamsung.Companion.app
import com.itos.xsamsung.ui.Pages.subassemblies.Opt.HDButton
import com.itos.xsamsung.ui.Pages.subassemblies.Opt.OptButton
import com.itos.xsamsung.ui.theme.OriginPlanTheme


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OptPage() {
    Scaffold(modifier = Modifier
        .fillMaxSize()
        .statusBarsPadding(),
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "优化")
                },
                actions = {

                    IconButton(
                        onClick = {
                            MaterialAlertDialogBuilder(app)
                                .setTitle("公告")
                                .setMessage(app.show_notice)
                                .setPositiveButton("了解") { dialog, which ->
                                    dialog.dismiss()
                                }
                                .show()
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.Notifications,
                            contentDescription = "help"
                        )
                    }
                }
            )
        }) {
        if (isLandscape(app)) {

            val scrollState = rememberScrollState()
            // 执行横屏时的操作
            Column(
                modifier = Modifier
                    .fillMaxSize(),
//                    .verticalScroll(scrollState)
//                    .padding(top = getStatusBarHeight().dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center // 将子项垂直居中
            ) {
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
                    OptButton()
//                    ProcessLimitButton()
                }

                Row (modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly){
                    HDButton()
//                    ControlSystemUpdateButton()
                }
//            Settings_opt()

            }
        } else {
            // 执行竖屏时的操作
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .statusBarsPadding(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center // 将子项垂直居中
            ) {
                OptButton()
//                ProcessLimitButton()
//            Settings_opt()
                HDButton()
//                ControlSystemUpdateButton()
            }
        }

    }
}

fun isLandscape(context: Context): Boolean {
    val configuration = context.resources.configuration
    return configuration.orientation == Configuration.ORIENTATION_LANDSCAPE
}

fun getStatusBarHeight(): Int {
    var result = 0
    val resourceId: Int = app.resources.getIdentifier("status_bar_height", "dimen", "android")
    if (resourceId > 0) {
        result = app.resources.getDimensionPixelSize(resourceId)
    }

    return result
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun a() {
    OriginPlanTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            OptPage()
        }
    }
}
