
FROM node:latest as build


WORKDIR /usr/local/app


COPY ./ /usr/local/app/

ENV NODE_OPTIONS=--openssl-legacy-provider

RUN npm install


RUN npm run build --prod


FROM nginx:alpine


COPY --from=build /usr/local/app/dist/time-tracker-webapp /usr/share/nginx/html

COPY nginx.site.template /etc/nginx/conf.d/default.conf

EXPOSE 80