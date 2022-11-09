import JSEncrypt from 'jsencrypt/bin/jsencrypt.min'

// 密钥对生成 http://web.chacuo.net/netrsakeypair

const publicKey = 'MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBAOjMUxkAkOkvyEorNRZAo8jN7w0EfOcD\n' +
  'y1K3S9cfbFIahcthDGZHUE3K6YhC+JLOOmwQLgl+dO6H1+ihhKnX0q8CAwEAAQ=='

const privateKey = 'MIIBVQIBADANBgkqhkiG9w0BAQEFAASCAT8wggE7AgEAAkEA6MxTGQCQ6S/ISis1\n' +
  'FkCjyM3vDQR85wPLUrdL1x9sUhqFy2EMZkdQTcrpiEL4ks46bBAuCX507ofX6KGE\n' +
  'qdfSrwIDAQABAkEAh0JgbcB0ceg/WUiteZZpEz0Dze5zm84Kjq7NM9qgFzsR4VCQ\n' +
  'qE95kQxVTruAWogWMzUWDn0bxDsvfjjuNNUL0QIhAP5h/8Eg4tlfhJ7eI4zvqp27\n' +
  'udD1Qg+lNDPUEi6IPaxbAiEA6kcydiUNwGj79ZWBI6j/pv5GkcN/b6T0lDfJKkkB\n' +
  'Ez0CIBZLoI7cwwUqSgViktsXZmzMt0yJMnEMmbaxL6bEaiNhAiEArXt/YIFbx4l2\n' +
  'WosVD+ULOwu8EiBkc7YA7ujIbCmXRDkCIGspCvrKoNfnTb8id3DZ3Fsz+Pw7R8dN\n' +
  'Hu8aboT/BQm3'

// 加密
export function encrypt(txt) {
  const encryptor = new JSEncrypt()
  encryptor.setPublicKey(publicKey) // 设置公钥
  return encryptor.encrypt(txt) // 对需要加密的数据进行加密
}

// 解密
export function decrypt(txt) {
  const encryptor = new JSEncrypt()
  encryptor.setPrivateKey(privateKey)
  return encryptor.decrypt(txt)
}

