<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:template match="report">
		<html>
		<head> <title> Report </title></head>
		<body>
			<h1>Current Report</h1>
	
			<table border="1">
			<tr>
			<td>Timestamp</td>
			<td>Timezone</td>
			<td>Version</td>
			</tr>
			<tr>
			<td> <xsl:value-of select="@timestamp"/> </td>
			<td> <xsl:value-of select="@timezone"/> </td>
			<td> <xsl:value-of select="@version"/> </td>
			</tr>
			</table>
			
			<br />
			<br />
			
			<table border="1">
			<tr>
			<td> <b>Metric Name</b></td>
			<td> <b>Timestamp</b></td>
			<td> <b>Value</b></td>
			<td> <b>Type</b></td>
			<td> <b>Unit</b></td>
			<td> <b>Spoof</b></td>
			<td> <b>Direction</b></td>
			</tr>
			
			<xsl:apply-templates/>
			</table>
		</body>
		</html>
	</xsl:template>
	
	<xsl:template match="metric_data">
	<tr>
		<td> <xsl:value-of select="metric_name"/> </td>
		<td> <xsl:value-of select="timestamp"/> </td>
		<td> <xsl:value-of select="value"/> </td>
		<td> <xsl:value-of select="type"/> </td>
		<td> <xsl:value-of select="units"/> </td>
		<td> <xsl:value-of select="spoof"/> </td>
		<td> <xsl:value-of select="direction"/> </td>
	</tr>
	</xsl:template>
</xsl:stylesheet>