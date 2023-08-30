package presentation.base

open class BpException(message: String) : RuntimeException(message)

open class InternetException(message: String) : BpException(message)
class NoInternetException(message: String) : InternetException(message)

open class AuthorizationException(message: String) : BpException(message)
class UnAuthorizedException(message: String) : AuthorizationException(message)
class PermissionDenied(message: String) : AuthorizationException(message)

open class RequestException(message: String) : BpException(message)
class ClientSideException(message: String) : RequestException(message)
class ServerSideException(message: String) : RequestException(message)
open class InvalidCredentialsException(message: String) : RequestException(message)
class UserNotFoundException(message: String) : InvalidCredentialsException(message)
class UserAlreadyExistException(message: String) : InvalidCredentialsException(message)
class InvalidUserNameException(message: String) : InvalidCredentialsException(message)
class InvalidPasswordException(message: String) : InvalidCredentialsException(message)
class UnknownErrorException(message: String) : RequestException(message)