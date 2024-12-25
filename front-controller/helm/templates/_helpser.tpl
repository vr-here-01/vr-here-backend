{{- define "api-gateway.name" -}}
api-gateway
{{- end -}}

{{- define "api-gateway.fullname" -}}
{{ .Release.Name }}-api-gateway
{{- end -}}
