const common = {
	client: 'postgresql',
	migrations: {
		directory: './src/main/migrations'
	},
	pool: {
		min: 2,
		max: 10
	}
}

module.exports = {
  development: Object.assign({
    connection: {
      database: 'abet_system_dev',
      user: "postgres",
      password: "postgres",
      port: '5432',
      host : '127.0.0.1'
    },
    seeds: {
      directory: './src/dev/seeds'
    }
	}, common),

  test: Object.assign({
    connection: {
      database: 'abet_system_test',
      user: "postgres",
      password: "postgres",
      port: '5432',
      host : '127.0.0.1'
    },
    seeds: {
      directory: './src/test/seeds'
    }
  }, common),

  production: Object.assign({
    connection: {
      database: 'abet_system',
      user: "postgres",
      password: "postgres",
      port: '5432',
      host : '127.0.0.1'
    }
  }, common)
};