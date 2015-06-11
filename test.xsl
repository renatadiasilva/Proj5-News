<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:template match="/">
		<html>
		<head> <title> Class </title></head>
		<body>
			<h1>Class qq-coisa</h1>
				
			<table border="1">
			<tr>
			<td> <b>Id</b></td>
			<td> <b>Name</b></td>
			<td> <b>Age</b></td>
			</tr>
			
			<xsl:apply-templates/>
			</table>
		</body>
		</html>
	</xsl:template>
	
	<xsl:template match="student">
	<tr>
		<td> <xsl:value-of select="@id"/> </td>
		<td> <xsl:value-of select="name"/> </td>
		<td> <xsl:value-of select="age"/> </td>
	</tr>
	</xsl:template>
</xsl:stylesheet>