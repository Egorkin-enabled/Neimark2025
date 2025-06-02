package ru.ivanovo_hack.preporate.features.forms

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material.Slider
import androidx.compose.material.Text
import androidx.compose.material3.Card
import androidx.compose.material3.RangeSlider
import androidx.compose.material3.SliderState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import preporate.composeapp.generated.resources.Res
import preporate.composeapp.generated.resources.from
import preporate.composeapp.generated.resources.value_short
import ru.ivanovo_hack.preporate.theme.AppTheme
import kotlin.math.pow
import kotlin.math.roundToInt

@Composable
fun FormsScreen(){
    Text(
        text = "Forms screen"
    )
}

@Composable
fun FormsView(){

}

@Composable
fun SliderFormsItem(
    label: String,
    value: Int,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
){
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(
            modifier = Modifier
                .padding( 10.dp)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = label,
                    style = TextStyle(
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                    ),
                    textAlign = TextAlign.Start,
                    modifier = Modifier,
                )

                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(100f))
                        .background(MaterialTheme.colorScheme.secondaryContainer)
                        .size(50.dp)
                ){
                    Text(
                        modifier = Modifier.align(Alignment.Center),
                        text = value.toString(),
                        color = MaterialTheme.colorScheme.onSecondaryContainer,
                        style = TextStyle(
                            fontSize = 36.sp,
                            fontWeight = FontWeight.Bold,
                        )
                    )
                }
            }
            content
        }
    }
}

@Preview
@Composable
fun SlidersFormItem_Preview(){
    var value by remember {
        mutableStateOf(1)
    }

    AppTheme {
        SliderFormsItem(
            label = "Актуальность знаний",
            value = value,
            modifier = Modifier
                .background(MaterialTheme.colorScheme.surfaceContainer)
                .fillMaxWidth(),
        ){
            CustomSlider(
                value = value,
                maxRange = 5,
                minRange = 1,
                onSliderChange = {value = it}
            )
        }
    }
}

@Composable
fun CustomSlider(
    value: Int,
    maxRange: Int,
    minRange: Int,
    onSliderChange: (Int) -> Unit
){
    var sliderPosition by remember {
        mutableStateOf(value)
    }

    Slider(
        modifier = Modifier.fillMaxWidth(),
        value = sliderPosition.toFloat(),
        valueRange = minRange.toFloat()..maxRange.toFloat(),
        steps = maxRange - minRange,
        onValueChange = {
            sliderPosition = it.toInt()
            onSliderChange(sliderPosition)
        },
    )
}