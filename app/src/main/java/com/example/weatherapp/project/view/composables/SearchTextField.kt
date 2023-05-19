package com.example.weatherapp.project.view.composables


import android.content.Intent
import android.util.Log
import androidx.activity.result.ActivityResultLauncher
import androidx.compose.foundation.clickable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weatherapp.R
import com.example.weatherapp.project.view.screens.search
import com.example.weatherapp.project.view.toggleProgressBar
import com.example.weatherapp.project.viewmodels.MainViewModel
import kotlinx.coroutines.CoroutineScope


@Composable
fun SearchTextField(
    modifier: Modifier,
    coroutineScope: CoroutineScope,
    mainViewModel: MainViewModel,

    searchGoogleMapsResult: ActivityResultLauncher<Intent>,
    searchIntent: Intent
) {
    var newChar by remember { mutableStateOf("") }

        TextField(
            //TODO - Google autocomplete must pop up on clicking the Text Field
            value = newChar,
            onValueChange = {
                //searchGoogleMapsResult.launch(searchIntent)
//                //TODO "newChar" value is not connected  to the value of Google autocomplete input char
//                if (newChar.length > 3) {
//                    val resultName = Autocomplete.getPlaceFromIntent(searchIntent).name as String
//
//                    newChar = resultName
//                } else
                    newChar = it
            },

            modifier = modifier
                .clickable(
                    onClick = {
                        Log.d("TAGA", "clicked")
                    }
                ),
            placeholder = {
                Text(
                    text = stringResource(id = R.string.search),
                    style = TextStyle(
                        fontSize = 24.sp,
                        color = colorResource(id = R.color.grey)
                    )
                )
            },
            leadingIcon = {
                Icon(
                    painter = painterResource(R.drawable.search),
                    contentDescription = "search icon"
                )
            },

            colors = TextFieldDefaults.textFieldColors(
                cursorColor = colorResource(R.color.black),
                textColor = colorResource(R.color.black),
                disabledTextColor = colorResource(R.color.transparent),
                backgroundColor = colorResource(R.color.lighter_grey_2),
                focusedIndicatorColor = colorResource(R.color.transparent),
                unfocusedIndicatorColor = colorResource(R.color.transparent),
                disabledIndicatorColor = colorResource(R.color.transparent),
                focusedLabelColor=colorResource(R.color.transparent)
            ),
            singleLine = true,
            shape = RoundedCornerShape(8.dp),
            keyboardOptions = KeyboardOptions.Default.copy(
                capitalization = KeyboardCapitalization.Characters,
                autoCorrect = false,
                imeAction = ImeAction.Search),
            keyboardActions = KeyboardActions(

                onSearch = {
                    toggleProgressBar(true)
                    search(coroutineScope,mainViewModel, newChar)
                    newChar = ""
                }
            )
        )
    }







