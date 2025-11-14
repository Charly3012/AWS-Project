package xyz.larchy.sicei.Models.Common;

import lombok.*;
import xyz.larchy.sicei.Models.Alumno;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ServiceResponse<T>{
    public boolean Success;
    public int HttpCode;
    public T Data;

    public static <T> ServiceResponse<T> Ok(T Data) {
        return new ServiceResponse<>(true, 200, Data);
    }

    public static <T> ServiceResponse<T> CreateSuccess(T Data) {
        return new ServiceResponse<>(true, 201, Data);
    }

    public static <T> ServiceResponse<T> BadRequest(T Data) {
        return new ServiceResponse<T>(false,  400, Data);
    }

    public static <T> ServiceResponse<T> NotFound(T Data) {
        return new ServiceResponse<T>(false,  404, Data);
    }
}
