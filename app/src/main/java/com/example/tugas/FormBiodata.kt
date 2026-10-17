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
