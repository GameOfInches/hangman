FROM node:14

EXPOSE 3000

RUN apt-get update && \
    apt-get install -y nodejs npm

COPY . /app/

WORKDIR /app

RUN npm init -y && \
    npm install

CMD ["npm", "start"]