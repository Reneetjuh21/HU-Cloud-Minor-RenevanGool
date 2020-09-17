FROM nginx:latest
COPY src/ /usr/share/nginx/html/
COPY resources/nginx.conf /etc/nginx/nginx.conf
EXPOSE 80

STOPSIGNAL SIGTERM

CMD ["nginx", "-g", "daemon off;"]