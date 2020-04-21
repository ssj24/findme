function token() {
  var value = document.cookie.match('(^|;) ?' + 'jwt-auth-token' + '=([^;]*)(;|$)');
  return value = value? value[2] : null
}
const cookie = {
  token:()=>token(),
}
export default cookie