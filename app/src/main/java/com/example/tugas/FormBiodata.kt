package com.example.tugas

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun FormBiodataScreen() {
    Scaffold(
        containerColor = colorResource(R.color.screen_background)
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            // Banner di bagian belakang
            BannerSection()

            // Box form MENGAPUNG di atas Scaffold / banner
            FormCard(modifier = Modifier.align(Alignment.BottomCenter))
        }
    }
}

@Composable
private fun BannerSection() {
    Box(modifier = Modifier.fillMaxWidth()) {
        Image(
            painter = painterResource(R.drawable.img_banner),
            contentDescription = stringResource(R.string.cd_banner),
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(dimensionResource(R.dimen.banner_height))
        )

        // Indikator dots (dekoratif)
        Row(
            modifier = Modifier.padding(
                top = dimensionResource(R.dimen.dot_padding_top),
                start = dimensionResource(R.dimen.dot_padding_start)
            ),
            horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.dot_spacing))
        ) {
            repeat(4) { index ->
                Box(
                    modifier = Modifier
                        .size(dimensionResource(R.dimen.dot_size))
                        .clip(CircleShape)
                        .background(
                            if (index == 0) colorResource(R.color.dot_active)
                            else colorResource(R.color.dot_inactive)
                        )
                )
            }
        }
    }
}

@Composable
private fun FormCard(modifier: Modifier = Modifier) {
    val context = LocalContext.current

    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var birthDate by remember { mutableStateOf("") }
    var experience by remember { mutableStateOf("") }

    val skillList = listOf(
        R.string.skill_android,
        R.string.skill_uiux,
        R.string.skill_web
    )
    val skillState = remember { mutableStateMapOf<Int, Boolean>() }

    val isFormValid = firstName.isNotBlank() &&
            lastName.isNotBlank() &&
            email.isNotBlank() &&
            birthDate.isNotBlank() &&
            experience.isNotBlank() &&
            skillState.values.any { it }

    val cardShape = RoundedCornerShape(
        topStart = dimensionResource(R.dimen.card_corner),
        topEnd = dimensionResource(R.dimen.card_corner)
    )

    Surface(
        modifier = modifier
            .fillMaxSize()
            .padding(top = dimensionResource(R.dimen.card_top_margin))
            .shadow(dimensionResource(R.dimen.card_elevation), cardShape),
        shape = cardShape,
        color = colorResource(R.color.surface_card)
    ) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(
                    horizontal = dimensionResource(R.dimen.card_padding_horizontal),
                    vertical = dimensionResource(R.dimen.card_padding_vertical)
                ),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(R.string.form_title),
                color = colorResource(R.color.purple_title),
                fontSize = dimensionResource(R.dimen.title_size).toSp(),
                fontWeight = FontWeight.Bold
            )
            Spacer(Modifier.height(dimensionResource(R.dimen.spacing_small)))
            Text(
                text = stringResource(R.string.form_subtitle),
                color = colorResource(R.color.text_subtitle),
                fontSize = dimensionResource(R.dimen.subtitle_size).toSp()
            )

            Spacer(Modifier.height(dimensionResource(R.dimen.spacing_large)))

            // Nama Depan & Nama Belakang
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.spacing_small))
            ) {
                FormTextField(
                    value = firstName,
                    onValueChange = { firstName = it },
                    hint = stringResource(R.string.hint_first_name),
                    modifier = Modifier.weight(1f)
                )
                FormTextField(
                    value = lastName,
                    onValueChange = { lastName = it },
                    hint = stringResource(R.string.hint_last_name),
                    modifier = Modifier.weight(1f)
                )
            }

            Spacer(Modifier.height(dimensionResource(R.dimen.spacing_medium)))

            // Email
            FormTextField(
                value = email,
                onValueChange = { email = it },
                hint = stringResource(R.string.hint_email),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(Modifier.height(dimensionResource(R.dimen.spacing_medium)))

            // Tgl Lahir & Experience
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.spacing_small))
            ) {
                BirthDateField(
                    value = birthDate,
                    onDateSelected = { birthDate = it },
                    modifier = Modifier.weight(1f)
                )
                ExperienceDropdown(
                    selected = experience,
                    onSelected = { experience = it },
                    modifier = Modifier.weight(1f)
                )
            }

            Spacer(Modifier.height(dimensionResource(R.dimen.spacing_medium)))

            // Skill
            Text(
                text = stringResource(R.string.label_skill),
                color = colorResource(R.color.text_primary),
                fontSize = dimensionResource(R.dimen.label_size).toSp(),
                fontWeight = FontWeight.Bold,
                modifier = Modifier.fillMaxWidth()
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                skillList.forEach { skillRes ->
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Checkbox(
                            checked = skillState[skillRes] ?: false,
                            onCheckedChange = { skillState[skillRes] = it },
                            colors = CheckboxDefaults.colors(
                                checkedColor = colorResource(R.color.purple_primary),
                                uncheckedColor = colorResource(R.color.text_primary)
                            )
                        )
                        Text(
                            text = stringResource(skillRes),
                            color = colorResource(R.color.text_primary),
                            fontSize = dimensionResource(R.dimen.skill_text_size).toSp()
                        )
                    }
                }
            }

            Spacer(Modifier.height(dimensionResource(R.dimen.spacing_large)))

            // Tombol Proceed
            val successMsg = stringResource(R.string.msg_success, firstName)
            Button(
                onClick = {
                    Toast.makeText(context, successMsg, Toast.LENGTH_SHORT).show()
                },
                enabled = isFormValid,
                shape = RoundedCornerShape(dimensionResource(R.dimen.button_corner)),
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(R.color.purple_primary),
                    contentColor = colorResource(R.color.button_text),
                    disabledContainerColor = colorResource(R.color.button_disabled),
                    disabledContentColor = colorResource(R.color.button_disabled_text)
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(dimensionResource(R.dimen.button_height))
            ) {
                Text(
                    text = stringResource(R.string.btn_proceed),
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Composable
private fun formFieldColors() = OutlinedTextFieldDefaults.colors(
    focusedBorderColor = colorResource(R.color.purple_primary),
    unfocusedBorderColor = colorResource(R.color.field_border),
    disabledBorderColor = colorResource(R.color.field_border),
    focusedTextColor = colorResource(R.color.text_primary),
    unfocusedTextColor = colorResource(R.color.text_primary),
    disabledTextColor = colorResource(R.color.text_primary),
    focusedPlaceholderColor = colorResource(R.color.text_hint),
    unfocusedPlaceholderColor = colorResource(R.color.text_hint),
    disabledPlaceholderColor = colorResource(R.color.text_hint)
)

@Composable
private fun FormTextField(
    value: String,
    onValueChange: (String) -> Unit,
    hint: String,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        placeholder = {
            Text(text = hint, fontSize = dimensionResource(R.dimen.field_text_size).toSp())
        },
        singleLine = true,
        shape = RoundedCornerShape(dimensionResource(R.dimen.field_corner)),
        colors = formFieldColors(),
        modifier = modifier.heightIn(min = dimensionResource(R.dimen.field_height))
    )
}
