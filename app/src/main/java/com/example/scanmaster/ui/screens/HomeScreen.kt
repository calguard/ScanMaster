package com.example.scanmaster.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.scanmaster.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(onNavigateToFiles: () -> Unit) {
    Scaffold(
        topBar = {
            Column(
                modifier = Modifier.background(MaterialTheme.colorScheme.background).statusBarsPadding().padding(horizontal = 24.dp, vertical = 16.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = stringResource(R.string.app_name_pro), style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
                    Box(
                        modifier = Modifier.size(40.dp).clip(CircleShape).background(MaterialTheme.colorScheme.primary.copy(alpha = 0.1f)).padding(8.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(imageVector = Icons.Default.AccountCircle, contentDescription = "Profile", tint = MaterialTheme.colorScheme.primary)
                    }
                }
                Spacer(modifier = Modifier.height(24.dp))
                TextField(
                    value = "",
                    onValueChange = {},
                    placeholder = { Text(stringResource(R.string.search_placeholder)) },
                    leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
                    trailingIcon = { Icon(Icons.Default.Tune, contentDescription = null) },
                    modifier = Modifier.fillMaxWidth().clip(RoundedCornerShape(16.dp)),
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.05f),
                        unfocusedContainerColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.05f),
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    )
                )
            }
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { },
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = Color.White,
                shape = CircleShape,
                modifier = Modifier.size(64.dp)
            ) {
                Icon(Icons.Default.PhotoCamera, contentDescription = "Scan", modifier = Modifier.size(32.dp))
            }
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier.fillMaxSize().padding(paddingValues).padding(horizontal = 24.dp)
        ) {
            item {
                Spacer(modifier = Modifier.height(8.dp))
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                    StatCard(title = stringResource(R.string.total_scans), value = "128", icon = Icons.Default.Description, modifier = Modifier.weight(1f))
                    StatCard(title = stringResource(R.string.google_drive), value = stringResource(R.string.active), icon = Icons.Default.CloudDone, modifier = Modifier.weight(1f))
                }
                Spacer(modifier = Modifier.height(32.dp))
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                    Text(text = stringResource(R.string.recent_scans), style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
                    TextButton(onClick = onNavigateToFiles) {
                        Text(stringResource(R.string.view_all), color = MaterialTheme.colorScheme.primary)
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
            }
            items(recentScans) { scan ->
                RecentScanItem(scan)
                Spacer(modifier = Modifier.height(12.dp))
            }
            item { Spacer(modifier = Modifier.height(80.dp)) }
        }
    }
}

@Composable
fun StatCard(title: String, value: String, icon: androidx.compose.ui.graphics.vector.ImageVector, modifier: Modifier = Modifier) {
    Column(modifier = modifier.clip(RoundedCornerShape(24.dp)).background(MaterialTheme.colorScheme.primary.copy(alpha = 0.05f)).padding(16.dp)) {
        Icon(imageVector = icon, contentDescription = null, tint = MaterialTheme.colorScheme.primary, modifier = Modifier.size(24.dp))
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = title, style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f))
        Text(text = value, style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
    }
}

@Composable
fun RecentScanItem(scan: ScanData) {
    Row(modifier = Modifier.fillMaxWidth().clip(RoundedCornerShape(20.dp)).background(MaterialTheme.colorScheme.primary.copy(alpha = 0.03f)).padding(12.dp), verticalAlignment = Alignment.CenterVertically) {
        AsyncImage(model = scan.thumbnailUrl, contentDescription = null, modifier = Modifier.size(64.dp).clip(RoundedCornerShape(12.dp)).background(MaterialTheme.colorScheme.primary.copy(alpha = 0.1f)), contentScale = ContentScale.Crop)
        Spacer(modifier = Modifier.width(16.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(text = scan.name, fontWeight = FontWeight.SemiBold, maxLines = 1, overflow = TextOverflow.Ellipsis)
            Text(text = "${scan.date} • ${scan.size}", style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5f))
        }
        IconButton(onClick = { }) {
            Icon(Icons.Default.MoreVert, contentDescription = "More", tint = Color.Gray)
        }
    }
}

data class ScanData(val name: String, val date: String, val size: String, val thumbnailUrl: String)
val recentScans = listOf(
    ScanData("Project Proposal.pdf", "Oct 24, 2023", "1.2 MB", "https://lh3.googleusercontent.com/aida-public/AB6AXuCy14Cnq2PM-laiPxjIKlLGFTIrv3S8SppzrFnNN_25kXf0DpqEMB9CUhyW2WxrqefUTVCbKjUEQX6uZB1OhltRV5z6bPI255SAYzQ40s6Lzio5bhHGzL9m6ImojSscBiITJudZUKvXliKrXKNfcqTiNdDdr2lnP0EE8DxQBLr9mutYl67fFIpaCV-bpvzaa1VMfGzBN76OtsJvtrYkCn7R03GE9SqrsCMxbSNNFMtGVhlzqj85gjYBW41pMpuFH4tGpz8iKyJg12Q"),
    ScanData("Grocery Receipt.jpg", "Oct 22, 2023", "450 KB", "https://lh3.googleusercontent.com/aida-public/AB6AXuDHZSD8jliLsofpIrNuBHiMIeTpAUhtA4Kn80Ikb7zjP-l1qjHAqIUHzntLoOv6i1M4xRzYhdBfoHZ4Tvs0pVkaerZVHm__rR_6GbJc9DKcgaMj1j14UiqNjPfitPAEJApU2pSdthEKI442zhDKWLxSqCXFtpaH0Vp8QDNT9N_NrqwC6ZaFnJa5ZgnFaxKpKyHnMDcjRuyq1Yzte9LmhREIB6n6hiqyW5fx9CazTO_8d6-4aQ4tf3ngahOAInt-LPV85oWgyyJyh3g"),
    ScanData("Meeting Notes.pdf", "Oct 20, 2023", "2.8 MB", "https://lh3.googleusercontent.com/aida-public/AB6AXuBbogUxU8CGCLB02yO9hpGCldLawXBIuwXrNyLly4PIfLKPKSuQ0ttcEWs1N2YzqV4LpMfUkFOwxfQXnIfIW-gBbK3bqPLr2BuOeuRLOYM_7Sfk6CZqpLV6o0GLoUzC18CJAllYiB4-yDb64jspWZpbxaapRyFL1urqGq4xcf8vT4Nl_HO1_mHVdlnuIhtIhMdS0pYyFKcLVawR9tSAOOZoHO6P8c2we2JiC-10Ybv2i7T5x2nP2UXuGr69t9ISoDjBLw-YMH7yhdI")
)
