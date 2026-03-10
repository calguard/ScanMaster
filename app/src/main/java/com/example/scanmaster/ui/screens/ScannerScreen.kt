package com.example.scanmaster.ui.screens

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.scanmaster.R
import com.example.scanmaster.ui.theme.Primary

@Composable
fun ScannerScreen() {
    var scanMode by remember { mutableStateOf("AUTO") }
    Box(modifier = Modifier.fillMaxSize().background(Color.Black)) {
        AsyncImage(model = "https://lh3.googleusercontent.com/aida-public/AB6AXuAzW0OL0fSH8EUWSBnKtpd9003Iaqdx0VCyumhyVDjFs0TfvR6ITK-DJTmb1-HZiHJg3c6VaLf2KYhIrQvgr9ISwahVLYKMORyRRMftHA1Mcv9HGe-ZRtqvBm-iEYetT1cNlobzNkIVcd1HF1-icHYIiCt1SklzO6Gcg0fxh-mQUIlIwa6O4vO_ZzSJ70AvNGYVQu09ClN4Nfpn9Xir9KzaRc4J4pC5snn22fF6tNDXxbMLKbtUNJk9cUK162HX5aVHaNP9DPeK4lw", contentDescription = "Camera Feed", modifier = Modifier.fillMaxSize(), contentScale = ContentScale.Crop)
        Canvas(modifier = Modifier.fillMaxSize()) {
            val canvasWidth = size.width
            val canvasHeight = size.height
            val guideWidth = canvasWidth * 0.8f
            val guideHeight = guideWidth * 1.414f
            val left = (canvasWidth - guideWidth) / 2
            val top = (canvasHeight - guideHeight) / 2
            drawRect(color = Color.Black.copy(alpha = 0.4f), size = size)
            drawRoundRect(color = Color.Transparent, topLeft = Offset(left, top), size = Size(guideWidth, guideHeight), cornerRadius = CornerRadius(12.dp.toPx(), 12.dp.toPx()), blendMode = BlendMode.Clear)
            drawRoundRect(color = Color(0xFF53D22D).copy(alpha = 0.5f), topLeft = Offset(left, top), size = Size(guideWidth, guideHeight), cornerRadius = CornerRadius(12.dp.toPx(), 12.dp.toPx()), style = Stroke(width = 2.dp.toPx()))

            // Draw Corner Indicators
            val cornerLength = 32.dp.toPx()
            val strokeWidth = 4.dp.toPx()
            val primaryColor = Primary

            // Top Left
            drawLine(color = primaryColor, start = Offset(left, top), end = Offset(left + cornerLength, top), strokeWidth = strokeWidth)
            drawLine(color = primaryColor, start = Offset(left, top), end = Offset(left, top + cornerLength), strokeWidth = strokeWidth)

            // Top Right
            drawLine(color = primaryColor, start = Offset(left + guideWidth, top), end = Offset(left + guideWidth - cornerLength, top), strokeWidth = strokeWidth)
            drawLine(color = primaryColor, start = Offset(left + guideWidth, top), end = Offset(left + guideWidth, top + cornerLength), strokeWidth = strokeWidth)

            // Bottom Left
            drawLine(color = primaryColor, start = Offset(left, top + guideHeight), end = Offset(left + cornerLength, top + guideHeight), strokeWidth = strokeWidth)
            drawLine(color = primaryColor, start = Offset(left, top + guideHeight), end = Offset(left, top + guideHeight - cornerLength), strokeWidth = strokeWidth)

            // Bottom Right
            drawLine(color = primaryColor, start = Offset(left + guideWidth, top + guideHeight), end = Offset(left + guideWidth - cornerLength, top + guideHeight), strokeWidth = strokeWidth)
            drawLine(color = primaryColor, start = Offset(left + guideWidth, top + guideHeight), end = Offset(left + guideWidth, top + guideHeight - cornerLength), strokeWidth = strokeWidth)
        }
        Row(modifier = Modifier.fillMaxWidth().statusBarsPadding().padding(16.dp), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
            IconButton(onClick = { }, modifier = Modifier.clip(CircleShape).background(Color.Black.copy(alpha = 0.4f))) { Icon(Icons.Default.FlashAuto, contentDescription = "Flash", tint = Color.White) }
            Row(modifier = Modifier.clip(CircleShape).background(Color.Black.copy(alpha = 0.6f)).padding(4.dp)) {
                listOf(stringResource(R.string.auto), stringResource(R.string.manual)).forEach { mode ->
                    val selected = scanMode == mode
                    Box(modifier = Modifier.clip(CircleShape).background(if (selected) MaterialTheme.colorScheme.primary else Color.Transparent).clickable { scanMode = mode }.padding(horizontal = 16.dp, vertical = 6.dp)) {
                        Text(text = mode, color = if (selected) Color.Black else Color.White, fontSize = 12.sp, fontWeight = FontWeight.Bold)
                    }
                }
            }
            IconButton(onClick = { }, modifier = Modifier.clip(CircleShape).background(Color.Black.copy(alpha = 0.4f))) { Icon(Icons.Default.Settings, contentDescription = "Settings", tint = Color.White) }
        }
        Box(modifier = Modifier.fillMaxWidth().align(Alignment.BottomCenter).padding(bottom = 120.dp, start = 32.dp, end = 32.dp)) {
            AsyncImage(model = "https://lh3.googleusercontent.com/aida-public/AB6AXuBb3F-ya_dJUW3xR8GOUp-myEeCdaGN7ldN10JwIi8sja8Cf5yhR_2Lg1G4qXGyqJ_-A6ULOUod_nfWBC8KxlrjZQLKyy-kt8OqVJWIZWXW6YSoJxgPvMUIWHspy7q2iChAXgvx_-iSeXS1mJPMdNyQMPbd166WqWFbu6kO1dzCMqpWSw5v-l5VuMHNuLS80wx2mgXIrLXH810mQ1iG2jrggXtgyGmB2CgIuFDCqmuPdp1wVi2ApL5zmPkctUaNubVIOnx_hLPGpI4", contentDescription = "Thumbnail", modifier = Modifier.size(56.dp).clip(RoundedCornerShape(8.dp)).border(2.dp, Color.White.copy(alpha = 0.5f), RoundedCornerShape(8.dp)).align(Alignment.CenterStart), contentScale = ContentScale.Crop)
            Box(modifier = Modifier.align(Alignment.Center), contentAlignment = Alignment.Center) {
                Box(modifier = Modifier.size(96.dp).border(2.dp, MaterialTheme.colorScheme.primary.copy(alpha = 0.5f), CircleShape))
                Button(onClick = { }, modifier = Modifier.size(80.dp), shape = CircleShape, colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary), contentPadding = PaddingValues(0.dp)) {
                    Icon(Icons.Default.Camera, contentDescription = "Capture", modifier = Modifier.size(40.dp), tint = Color.Black)
                }
            }
            Box(modifier = Modifier.size(56.dp).clip(CircleShape).background(Color.Black.copy(alpha = 0.4f)).align(Alignment.CenterEnd), contentAlignment = Alignment.Center) {
                Icon(Icons.Default.Description, contentDescription = "Count", tint = Color.White)
                Box(modifier = Modifier.align(Alignment.TopEnd).size(24.dp).clip(CircleShape).background(MaterialTheme.colorScheme.primary).border(2.dp, Color.Black, CircleShape), contentAlignment = Alignment.Center) {
                    Text("3", color = Color.Black, fontSize = 10.sp, fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}
