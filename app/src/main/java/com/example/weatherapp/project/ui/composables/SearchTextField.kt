package com.example.weatherapp.project.ui.composables


import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import com.example.weatherapp.project.ui.screens.search
import com.example.weatherapp.project.viewmodels.MainViewModel
import kotlinx.coroutines.CoroutineScope


@Composable
fun SearchTextField(
    modifier: Modifier,
    coroutineScope: CoroutineScope,
    mainViewModel: MainViewModel,

) {
    var newChar by remember { mutableStateOf("") }

        TextField(
            value = newChar,
            onValueChange = {
                newChar=it
            },

            modifier = modifier,
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
                backgroundColor = colorResource(R.color.almost_white),
                focusedIndicatorColor = colorResource(R.color.transparent),
                unfocusedIndicatorColor = colorResource(R.color.transparent),
                disabledIndicatorColor = colorResource(R.color.transparent),
                focusedLabelColor=colorResource(R.color.transparent)
            ),
            singleLine = true,
            shape = RoundedCornerShape(14.dp),
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







